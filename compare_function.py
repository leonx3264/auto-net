from twilio.rest import Client
import re

#compare function takes both dhcp tables and compares them
#if there are any differences it will send a message via pushbullet
def compare(output, account, token):

    #input twilio info
    account_sid = account
    auth_token = token

    #create twilio client
    client = Client(account_sid, auth_token)

    #setting text files
    fname1 = "table.txt"
    fname2 = output

    #open text files
    f1 = open(fname1)
    f2 = open(fname2)

    #read first line of each text files
    f1_line = f1.readline()
    f2_line = f2.readline()

    #initialize line counter
    line_no = 1

    #array to store differences in files
    different = []

    #while text files do not end
    while f1_line != '' or f2_line != '':

        #remove whitespaces
        f1_line = f1_line.rstrip()
        f2_line = f2_line.rstrip()

        #if lines are different
        if f1_line != f2_line:
            if f1_line == '' and f2_line != '':
                #print(f2_line)
                #removes the ansi escape sequences from line
                ansi_escape = re.compile(r'\x1b\[[0-?]*[ -/]*[@-~]')
                new_line = ansi_escape.sub('', f2_line)
                #add the different line to the different array
                different.append(new_line)

        #read the next line
        f1_line = f1.readline()
        f2_line = f2.readline()

        #add to loop count
        line_no += 1

        #finish loop after all the lines are read

    #close the files
    f1.close()
    f2.close()

    #place message in variable
    send = ("new clients: \n" + str(different))

    #if there is a difference, send message with twilio
    if len(different) != 0:
        message = client.messages.create(
            to='your number',
            from_='twilio number',
            body=send)
