import paramiko
import time

#login function connects to host via ssh with username
#and password and then runs a command on that host
def login(host,user,passwd,commnd):
    client = paramiko.SSHClient()

    #accepts host keys not found on system
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    #connects to ssh server
    client.connect(host, username = user, password = passwd)

    #command to shell
    command = client.invoke_shell()
    command.send(commnd)

    #recieve reply, decode from bytes, place in output
    time.sleep(1)
    output = command.recv(65535).decode()

    #close connection
    client.close()

    #return the output of the command
    return(output)
