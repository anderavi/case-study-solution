#Points
- To run this project, the system should have Tesseract lib environment
- Used Tesseract-OCR in Ubuntu environment. Below given steps are for Ubuntu.For windows instructions link is given.
- I have saved the given image in pdfs to .png files. The 'data' folder has the saved images.
- Added appropriate Exception handling and added a simple test case. Below steps given for running.

#Setting up Tesseract lib environment

-On Ubuntu

sudo add-apt-repository ppa:ubuntu-toolchain-r/test 
sudo apt-get update
sudo apt-get install libstdc++6

-For Windows

Follow http://tess4j.sourceforge.net/

#Setting up project and running

-Command to run from project 'imagejson'

mvn clean install -DbillsLocation=<folder path to bills. end with a ‘/’ >

The text and json folder will be generated in the same billsLocation folder

Project also available in github @ 
