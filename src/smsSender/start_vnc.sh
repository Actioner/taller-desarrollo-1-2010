#! /bin/bash

resolution=$(dialog --stdout --menu "Choose resolution" 10 25 20 1 640x480 2 800x600 3 1280x1024)

if [ "$resolution" == "" ];then
	clear
	echo "Cancelled."
	exit 1
fi

color_depth=$(dialog --stdout --menu "Choose color depth" 10 25 20 1 "8 bits" 2 "16 bits" 3 "32 bits")
clear

if [ "$color_depth" == "" ];then
	clear
	echo "Cancelled."
	exit 1
fi

#kill previous session
tightvncserver -clean -kill :1 2> /dev/null

#setup atributes
case $resolution in
	1) geometry="640x480";;
	2) geometry="800x600";;
	3) geometry="1280x1024";;
esac

case $color_depth in
	1) depth="8";;
	2) depth="16";;
	3) depth="32";;
esac

tightvncserver -geometry "$geometry" -depth "$depth" -pixelformat rgb565 :2
