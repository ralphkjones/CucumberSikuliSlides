# Cucumber Sikuli Slides Cucumber JVM SikuliX CucumberSikuliSlides

This is a simple build script setup for using Sikuli Slides with Cucumber JVM in the step definitions.

The project tests three datasets in a DataTable:

@googleSearch
Feature: Google Searches
  
  In order to learn the sikuli slides interface to cucumber JVM, I want to
  use Google to search for different subjects and view different results

  Scenario Outline: Search for a few things at www.google.com
    Given I have opened google as my search engine
    When I enter "searchString"
    Then I should see "powerPointFile" "helloBlurb"

    Examples: 
      | searchString  | powerPointFile    | helloBlurb           |
      | amazon.com    | amazon.pptx       | Hello Amazon!        |
      | sikuli slides | sikuliSlides.pptx | Hello Sikuli Slides! |
      | cucumber jvm  | cucumberJvm.pptx  | Hello Cucumber JVM!  |


This first dataset will execute correctly. The other datasets execute correctly as far as the java. They don't
work because the slides need to be edited in Powerpoint in order to work. I purposely left them as not working
so you could see  a passed test and a failed test. The problem is not with cucumber, java or sikuli slides;
the problem is in the image recognition of the powerpoint slide to the screen

# Problems, etc ...

I could only make this run in the Eclipse IDE. I could not make it run in maven because I could not use the
external image recognition jars in my local repository. I had to add them in as external jars in my eclipse
project.

Running mvn test goes to google and then fails on image recognition:

Running googleSearch.RunCukesTest
203 [main] INFO org.sikuli.slides.api.AutomationExecutor - Execute slide 1 of 1
387 [main] INFO org.sikuli.slides.api.AutomationExecutor - Execute slide 1 of 2
Exception in thread "Thread-2" java.lang.UnsatisfiedLinkError: no jniopencv_core in java.library.path
        at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1857)
        at java.lang.Runtime.loadLibrary0(Runtime.java:870)

See blog at http://mmstratton.com/wp/javacv-setup/ for information: Its quoted here

To correct:

## Installing OpenCV

*JavaCV is only a wrapper around OpenCV, so you have to have OpenCV first for anything to work. Sometimes it can be a huge pain installing libraries in a windows environment. Luckily there are working binaries for OpenCV. All you need to do is download and install them. The installer is really just a self-extracting zip file though, so once OpenCV has been extracted we need to add the bin/ folders to out PATH system variable, otherwise it wont be useable.*

*Download OpenCV-2.3.1-win-superpack.exe*
*Install to C:\*
*Add C:\opencv\build\x64\vc10\bin;C:\opencv\build\common\tbb\intel64\vc10\ to your PATH system variable*
*If you use a newer version of OpenCV you might end up adding a different path. Most people didn’t have put*
*C:\opencv\build\common\tbb\intel64\vc10\ on their PATH, but it seems very important for the version I have.*

*I also saw several people insisting that it was very important that OpenCV be installed to the root*
*of your C drive. While I am pretty sure you should be able to move it around anywhere and it work,*
*as long as your PATH is set correctly, after several hours debugging you tend to start dropping assumptions*
*like that and taking safe routes. Extract OpenCV anywhere you want, but if you are having trouble, try*
*moving it to C:\*

## Installing JavaCV

*If you are a Maven fan, like myself, you might be tempted to just add a dependency to your prom file like so*

```
<dependency>
  <groupId>org.bytedeco</groupId>
  <artifactId>javacv</artifactId>
  <version>0.9</version>
</dependency>
```

*Their github page even suggests it saying we can also have everything downloaded and installed automatically.*
*While this will get JavaCV set up with your java project, it unfortunately will not*
*play so well with your OpenCV native libraries you installed in the last step.*
*Or it didn’t for me. I could not find a way to get the maven dependency working.*
*And I wanted it to work so badly.*

*Instead we will install the Jar files manually and add them to the projects build path.*

*Download javacv-0.9-bin.zip*
*Extract the zip file to anywhere, I chose C:\lib*
*Remember where you extract JavaCV, we will need this location when configuring the build path next.*

*Add JavaCV to your project’s build path in Eclipse*

*We need to point our project to the JavaCV jar files we extracted in the previous step. There are plenty*
*of examples out there about how to do this so I wont go in to too much detail, but the basics in Eclipse are:*

*Project > Properties > Java Build Path > Libraries > Add External JARs*
*Navigate to the location of JavaCV on your file system, for me that is C:\lib\javacv-bin*
*Add all the jars, yes all of them.*
*You should be good to go now. If not, you might need to read the next section.*

# feature and pptx path:

src\test\resources\googleSearch

# One Jar

I would love to have this as a one jar executable. I have never been able to have the one jar
class loader find the glue. In the name of P. Simon Tufts!


