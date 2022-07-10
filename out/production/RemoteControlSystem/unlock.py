import pyautogui
import sys

#this line of code accepts an argument from the command then execute it
#used to get the total length of the argument
#total = len(sys.argv);
#for index in range(1,total):

#writes to the console or password field
#a list of arguments
#print(str(sys.argv[1]));
pyautogui.write(str(sys.argv[1]));
#this line of code presses enter key after it writes down
pyautogui.press('enter');
