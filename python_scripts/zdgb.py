'''
Created on 19-Jun-2013

@author: PRANNOY.P
'''
import csv
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))

row_y=4
row_x=0

a=[]
b=[]
mon=[]
i=0
c=0
for row1 in cr:
    if i==0:
        i=1
        header1=row1[row_y].replace(" ","")
        header2=row1[row_x].replace(" ","")
    elif (row1[14]=="IN" and row1[8]=="00"):
        if (row1[row_y]!="" and row1[row_x]!=""):            
            a.append(row1[row_y].strip())
            b.append(row1[row_x].strip())
            mon.append((row1[1]+";"+row1[2]))
y=set(a)
z=set(b)
mont=set(mon)
y_axis={'Dummy':0.0}
x_axis={'Dummy':0}

for j in y:
    y_axis[j]=0.0
for k in z:
    x_axis[k]=0
del y_axis['Dummy']
del x_axis['Dummy']
#print x_axis
print y_axis
y_axis={'Dummy':0.0}
x_axis={'Dummy':0}

for j in y:
    y_axis[j]=0.0
for k in z:
    x_axis[k]=0
del y_axis['Dummy']
del x_axis['Dummy']

for filt in mont:
    for w in y_axis:
        y_axis[w]=0
    for e in x_axis:
        x_axis[e]=0
    a=0.0
    table={'Dummy':0}
    for a in y_axis:
        for b in x_axis:
            table[(a,b)]=0
    del table['Dummy']     
    #print table
    #for calculating amount spent in various super categories and hour
    cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))
    i=0
    for row in cr:
        if i==0:
            i += 1
        elif (row[14]=="IN" and row[8]=="00"):
            a=float(row[7].replace(",",""))
            if (row[row_y]!="" and row[row_x]!="" ):
                if (row[1]+";"+row[2])==filt:                                                                   
                    y_axis[row[row_y].strip()]+= 1
                    table[(row[row_y].strip(),row[row_x].strip())]+=1
                    x_axis[row[row_x].strip()] += 1
                    
    
    #Writing into csv   
    c = csv.writer(open("F:\\{}_{}_{}.csv".format(header1,header2,filt), "wb"))    
    x=[]   
    y=[]
    y.append("{}/{}".format(header1,header2))
    
    
    for i in x_axis:
        y.append(i)
        
    c.writerow(y)
    for i in y_axis:
        x.append(i)
        for j in x_axis:
            x.append(table[(i,j)])
        c.writerow(x)
        x=[]
        
 
     
    
    
    

    
    