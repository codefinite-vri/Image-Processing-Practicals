#EXP 2B

import cv2
 
img = cv2.imread('j2.jpg')
cv2.imshow("Original Image",img)
img_not = cv2.bitwise_not(img)
cv2.imshow("Inverted Image",img_not)
cv2.imwrite('exp3bop.jpg', img_not) 
cv2.waitKey(0)
cv2.destroyAllWindows()