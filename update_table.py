###########################################################
#This script should be run first before the main script.
#Without running this there will be no table.txt to compare
#the current dhcp table to and the program will not run.
###########################################################

from ssh_function import login

def update():
    #log in info
    host = '10.0.0.1'
    user = 'ubnt'
    password = 'root'
    command = 'show dhcp leases \n'

    #save command output to variable
    dhcp = login(host,user,password,command)

    #sets the first table to compare the results to
    file = open("table.txt","w")
    file.write(str(dhcp))
    file.close()

update()
