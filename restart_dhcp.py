#!/usr/bin/env python3

#this script restarts the dhcp service

from ssh_function import login

def refresh():
    #log in info
    host = '10.0.0.1'
    user = 'ubnt'
    password = 'root'
    command = 'release dhcp interface eth0 \n'

    #runs and saves to variable
    run = login(host, user, password, command)

refresh()
