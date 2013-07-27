import csv
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))

row_y=4
row_x=12

a=[]
b=[]
i=0
c=0
for row1 in cr:
    if i==0:
        i=1
        header1=row1[row_y]
        header2=row1[row_x]
    elif (row1[14]=="IN" and row1[8]=="00"):
        if (row1[row_y]!="" and row1[row_x]!=""):
            a.append(row1[row_y])
            b.append(row1[row_x])
y=set(a)
z=set(b)
y_axis={'Dummy':0.0}
x_axis={'Dummy':0}

for j in y:
    y_axis[j]=0.0
for k in z:
    x_axis[k]=0
del y_axis['Dummy']
del x_axis['Dummy']
print x_axis
print y_axis

a=0.0
table={'Dummy':0}     
#print table
#for calculating amount spent in various super categories and hour
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))
i=0
for row in cr:
    if i==0:
        i += 1
    elif (row[14]=="IN" and row[8]=="00"):
        a=float(row[7].replace(",",""))
        if (row[row_y]!="" and row[row_x]!=""):                        
            y_axis[row[row_y]]+= 1
            if (row[row_y],row[row_x]) in table.keys():
                table[(row[row_y],row[row_x])]+=1
            else:
                table[(row[row_y],row[row_x])]= 0
        x_axis[row[row_x]] += 1



#Writing into csv   
c = csv.writer(open("F:\\{}_{}.csv".format(header1,header2), "wb"))    
x=[]   
x_axis=sorted(x_axis.keys())
y_axis=sorted(y_axis.items(),key=lambda x: x[1],reverse=True)
table=sorted(table.items(),key=lambda x: x[1],reverse=True)
print table

'''
for i,k in y_axis:
    x.append(i)
    for j,l,m in table:
        x.append()
    c.writerow(x)
    x=[]
'''

        