#EXP 2B

import cv2

img = cv2.imread('j2.jpg')
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
cv2.imshow('Original image',img)
cv2.imshow('Gray image', gray)
cv2.imwrite('exp2bop.jpg', gray) 
cv2.waitKey(0)
cv2.destroyAllWindows()