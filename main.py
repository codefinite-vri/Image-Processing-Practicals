from tkinter import *

import numpy as np
from PIL import ImageTk, Image
from tkinter import filedialog
import cv2

imgX = Image.open("Q2.png")
x = None


# open image function
def open_img():
    # Select the ImageName from a folder
    global x
    x = openfilename()
    # opens the image
    img = Image.open(x)
    # resize the image and apply a high-quality down sampling filter
    img = img.resize((640, 480), Image.ANTIALIAS)
    # PhotoImage class is used to add image to widgets, icons etc
    img = ImageTk.PhotoImage(img)
    # create a label
    panel = Label(root, image=img)
    # set the image as img
    panel.image = img
    panel.grid(row=2, column=1)


# function to get file name
def openfilename():
    # open file dialog box to select image
    # The dialogue box has a title "Open"
    filename = filedialog.askopenfilename(title='Open')
    return filename


# transform to grayscale using PIL
def gray():
    global imgX
    imgX = Image.open(x)
    imgX = imgX.resize((640, 480), Image.ANTIALIAS).convert('L')
    imgX1 = ImageTk.PhotoImage(imgX)
    panel = Label(root, image=imgX1)
    panel.image = imgX1
    panel.grid(row=2, column=2)
    return


# invert image using cv2
def invert():
    global imgX
    # read & process image using cv2
    imgX1 = cv2.imread(x)
    imgX1 = cv2.bitwise_not(imgX1)

    # convert cv2 image to imageTk
    b, g, r = cv2.split(imgX1)
    imgX1 = cv2.merge((r, g, b))
    print(imgX1)
    imgX = Image.fromarray(imgX1)

    imgX = imgX.resize((640, 480), Image.ANTIALIAS)
    imgX1 = ImageTk.PhotoImage(image=imgX)
    panel = Label(root, image=imgX1)
    panel.image = imgX1
    panel.grid(row=2, column=2)


def grayslice():
    global imgX
    # read & process image using cv2
    imgX1 = cv2.imread(x, 0)
    # Find width and height of image
    row, column = imgX1.shape

    # Specify the min and max range
    min_range = 10
    max_range = 100

    # Loop over the input image and if pixel value lies in desired range set it to 255 otherwise set it to 0.
    for i in range(row):
        for j in range(column):
            if min_range < imgX1[i, j] < max_range:
                imgX1[i, j] = 255
            else:
                imgX1[i, j] = 0

    # convert cv2 image to imageTk
    imgX = Image.fromarray(imgX1)

    imgX = imgX.resize((640, 480))
    imgX1 = ImageTk.PhotoImage(image=imgX)
    panel = Label(root, image=imgX1)
    panel.image = imgX1
    panel.grid(row=2, column=2)
    return


# Bit plane slicing
def bitPlaneSlice():
    global imgX
    imgX = cv2.imread(x, 0)
    # Iterate over each pixel and change pixel value to binary using np.binary_repr() and store it in a list.
    lst = []
    for i in range(imgX.shape[0]):
        for j in range(imgX.shape[1]):
            lst.append(np.binary_repr(imgX[i][j], width=8))  # width = no. of bits

    # We have a list of strings where each string represents binary pixel value. To extract bit planes we need to
    # iterate over the strings and store the characters corresponding to bit planes into lists. Multiply with 2^(n-1)
    # and reshape to reconstruct the bit image.
    eight_bit_img = (np.array([int(i[0]) for i in lst], dtype=np.uint8) * 128).reshape(imgX.shape[0], imgX.shape[1])
    seven_bit_img = (np.array([int(i[1]) for i in lst], dtype=np.uint8) * 64).reshape(imgX.shape[0], imgX.shape[1])
    six_bit_img = (np.array([int(i[2]) for i in lst], dtype=np.uint8) * 32).reshape(imgX.shape[0], imgX.shape[1])
    five_bit_img = (np.array([int(i[3]) for i in lst], dtype=np.uint8) * 16).reshape(imgX.shape[0], imgX.shape[1])
    four_bit_img = (np.array([int(i[4]) for i in lst], dtype=np.uint8) * 8).reshape(imgX.shape[0], imgX.shape[1])
    three_bit_img = (np.array([int(i[5]) for i in lst], dtype=np.uint8) * 4).reshape(imgX.shape[0], imgX.shape[1])
    two_bit_img = (np.array([int(i[6]) for i in lst], dtype=np.uint8) * 2).reshape(imgX.shape[0], imgX.shape[1])
    one_bit_img = (np.array([int(i[7]) for i in lst], dtype=np.uint8) * 1).reshape(imgX.shape[0], imgX.shape[1])

    # Concatenate these images for ease of display using cv2.hconcat()
    finalR = cv2.hconcat([eight_bit_img, seven_bit_img, six_bit_img, five_bit_img])
    finalV = cv2.hconcat([four_bit_img, three_bit_img, two_bit_img, one_bit_img])

    # Vertically concatenate
    final = cv2.vconcat([finalR, finalV])

    # display edited image.
    imgX = Image.fromarray(final)
    imgX = imgX.resize((640, 480), Image.ANTIALIAS)
    imgX1 = ImageTk.PhotoImage(image=imgX)
    panel = Label(root, image=imgX1)
    panel.image = imgX1
    panel.grid(row=2, column=2)
    return


# Contrast Stretching
def contrastStretch():
    global imgX
    # Open the image.
    imgX1 = cv2.imread(x, 0)
    # Define parameters.
    r1 = 100
    s1 = 0
    r2 = 200
    s2 = 255
    row, column = imgX1.shape
    for i in range(row):
        for j in range(column):
            if 0 <= imgX1[i, j] <= r1:
                imgX1[i, j] = (s1 / r1) * imgX1[i, j]
            elif r1 < imgX1[i, j] <= r2:
                imgX1[i, j] = ((s2 - s1) * (imgX1[i, j] - r1) / (r2 - r1)) + s1
            else:
                imgX1[i, j] = ((255 - s2) * (imgX1[i, j] - r2) / (255 - r2)) + s2

    # display edited image.
    imgX = Image.fromarray(imgX1)
    imgX = imgX.resize((640, 480), Image.ANTIALIAS)
    imgX1 = ImageTk.PhotoImage(image=imgX)
    panel = Label(root, image=imgX1)
    panel.image = imgX1
    panel.grid(row=2, column=2)
    return


# save image to directory
def save():
    image = imgX
    image.save("Q2.png")
    return


# Create a window
root = Tk()
# Set Title as Image Loader
root.title("Image Processing")
# Set the resolution of window
root.geometry("1920x1080")
# Allow Window to be resizable
root.resizable(width=True, height=True)
# Create a button and place it into the window using grid layout
btnOp = Button(root, text='Open', command=open_img).grid(row=1, column=1, padx=300, pady=10)
btnSave = Button(root, text='save', command=save).grid(row=1, column=2, padx=300, pady=10)

imag = Image.open('Q.jpg')
imag = imag.resize((640, 480), Image.ANTIALIAS)
imag = ImageTk.PhotoImage(imag)
panel1 = Label(root, image=imag)
panel2 = Label(root, image=imag)
panel1.image = imag
panel2.image = imag
panel1.grid(row=2, column=1)
panel2.grid(row=2, column=2)

# btnGray = Button(root, text='grayscale', command=gray).grid(row=3, column=1, padx=300, pady=10)
# btnInv = Button(root, text='Invert', command=invert).grid(row=4, column=1, padx=300, pady=10)
# btnGSlice = Button(root, text='Intensity Slicing', command=grayslice).grid(row=5, column=1, padx=300, pady=10)
# btnBPSlice = Button(root, text='Bit Plane Slicing', command=bitPlaneSlice).grid(row=5, column=1, padx=300, pady=10)
btnCS = Button(root, text='Contrast Stretch', command=contrastStretch).grid(row=6, column=1, padx=300, pady=10)
root.mainloop()
