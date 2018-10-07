#!/usr/bin/env python3

#this script reboots the router

from ssh_function import login

def reboot():
    #log in info
    host = '10.0.0.1'
    user = 'ubnt'
    password = 'root'
    command = 'reboot now \n'

    #save command output to variable
    run = login(host,user,password,command)

reboot()
