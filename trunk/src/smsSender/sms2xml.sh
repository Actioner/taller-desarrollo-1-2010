#!/bin/sh

#This script converts a received text message to XML.
#Based on a script written by Thomas Dolberg, October 2005


MESSAGE_STARTING_LINE=14

#run this script only when a message was received.
if [ "$1" != "RECEIVED" ]; then exit; fi;

#Extract data from the SMS file
FROM=Numero:$(cat $2 | sed -n 's/^From: \(.*\)$/\1/p')
MESSGE=Mensaje:$(cat $2 | tail -n +$MESSAGE_STARTING_LINE)

FILE_NAME="$2.tmp"

#Save as XML

touch "$FILE_NAME"

echo "$FROM" >"$FILE_NAME"
echo "$MESSGE" >>"$FILE_NAME"

rm "$2"
mv "$FILE_NAME" "$2"