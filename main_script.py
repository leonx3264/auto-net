#!/usr/bin/env python3

##################################################################
#This is the main script for this program. Running this
#assumes that you have run the update_table script already.
#
#If you have not run the update_table script this will not work
#as it is dependent on that script.
##################################################################

from ssh_function import login
from compare_function import compare
from update_table import update
import time

print("press Ctrl+C to exit")

while True:

    #twilio info
    account = 'twilio account id'
    token = 'twilio account token'

    #login info
    host = '10.0.0.1'
    user = 'ubnt'
    password = 'root'
    command = 'show dhcp leases \n'

    #save command output to variable
    current_dhcp = login(host,user,password,command)

    #writes output to text file
    file = open("current.txt","w")
    file.write(str(current_dhcp))
    file.close()

    #initialize the text file variable
    to_compare = "current.txt"

    #call the compare funcion to compare to current table
    #and send the twilio sms
    compare(to_compare, account, token)

    #updates main table for comparison
    update()

    time.sleep(10)
