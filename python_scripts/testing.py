'''
Created on 15-Jul-2013

@author: PRANNOY.P
'''
import csv
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))

row_y=4
row_x=1

a=[]
b=[]
i=0
c=0
for row1 in cr:
    if i==0:
        i=1
        header1=row1[row_y]
    elif (row1[14]=="IN" and row1[8]=="00"):
        if (row1[row_y]!="" and row1[row_x]!=""):
            a.append(row1[row_y])
y=set(a)

y_axis={'Dummy':0.0}
amount={'Dummy':0.0}
active={'Dummy':0.0}

for j in y:
    y_axis[j]=0.0
    amount[j]=0.0
    
del y_axis['Dummy']


a=0.0
table={'Dummy':0}
avgt={'Dummy':0}
freq={'Dummy':0}

def period(cardNum):
    global table
    if (table[cardNum,"End date"]-table[cardNum,"Start date"] < 0):
        table[cardNum,"End date"]+=(12-table[cardNum,"Start date"])
        print table[cardNum,"End date"],table[cardNum,"Start date"]
    t=table[cardNum,"End date"]-table[cardNum,"Start date"]
    if t==0:
        return 1
    elif t>0:
        return t
    else:
        return -t
        
    
#print table
#for calculating amount spent in various super categories and hour
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))
i=0
for row in cr:
    if i==0:
        i += 1
    elif (row[14]=="IN" and row[8]=="00"):
        a=float(row[7].replace(",",""))
        b=int(row[1])
        if (row[row_y]!="" and row[row_x]!=""):                        
            y_axis[row[row_y]]+= 1
            amount[row[row_y]]+=a
            if (row[row_y],"Start date") in table.keys():
                table[row[row_y],"End date"]= b
            else:
                table[row[row_y],"Start date"]= b
                table[row[row_y],"End date"]= b 
for i in y_axis:
    t=period(i)
    active[i]=t
    avgt[i]=  y_axis[i]/t
    amount[i]=amount[i]/t
    freq[i]=(30*t)/ y_axis[i]
    
del table['Dummy']
#Writing into csv   
c = csv.writer(open("F:\\{}_transaction frequency.csv".format(header1), "wb"))    
x=[]   
y=[]
y.append("{}".format(header1))
y.append("Total num of Transactions")
y.append("Num of Months since activation")
y.append("Transactions/month")
y.append("Expenditure/month")
y.append("Frequency of transaction in days")
y_axis=sorted(y_axis.items(),key=lambda x: x[1],reverse=True)


    
c.writerow(y)
for i,k in y_axis:
    x.append(i)
    x.append(k)
    x.append(active[i])
    x.append(avgt[i])
    x.append(amount[i])
    x.append(freq[i])
    c.writerow(x)
    x=[]
