'''
Created on 02-Jul-2013

@author: PRANNOY.P
'''
import csv
import math
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))

row_y=12
row_x=0

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
for a in y_axis:
    for b in x_axis:
        table[(a,b)]=0
del table['Dummy']     
#print table
#for calculating amount spent in various super categories and hour
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))
i=0
n=0
for row in cr:
    if i==0:
        i += 1
    elif (row[14]=="IN" and row[8]=="00"):
        a=float(row[7].replace(",",""))
        if (row[row_y]!="" and row[row_x]!=""):                        
            y_axis[row[row_y]]+= 1
            table[(row[row_y],row[row_x])]+=1
            x_axis[row[row_x]] += 1
            n += 1

#Writing into csv   
c = csv.writer(open("F:\\{}_{}.csv".format(header1,header2), "wb"))
cv = csv.writer(open("F:\\{}_{}1.csv".format(header1,header2), "wb"))     
x=[]   
y=[]
y.append("{}/{}".format(header1,header2))
x_axis=sorted(x_axis.keys())
y_axis=sorted(y_axis.items(),key=lambda x: x[1],reverse=True)

for i in x_axis:
    y.append(i)
    
c.writerow(y)
cv.writerow(y)
for i,k in y_axis:
    x.append(i)
    for j in x_axis:
        x.append((table[(i,j)]*k)/k)
    c.writerow(x)
    cv.writerow(x)
    x=[]
aa=0.0
ab=0.0
bb=0.0
#function to calculate colrelattion
def corelation(a,b):
    global aa,ab,bb
    aa += a**2
    bb += b**2
    ab += a*b
        
cr = csv.reader(open("F:\\{}_{}.csv".format(header1,header2), "rb"))
i=0
j=0
mean={'Dummy':0}
corel={'Dummy':0}
for i,k in y_axis:
    mean[i]=(k/28)
del mean['Dummy']
print mean
for row in cr:
    if i==0:
        i=1
    else:
        c = csv.reader(open("F:\\{}_{}1.csv".format(header1,header2), "rb"))
        for r in c:
            if j==0:
                j += 1
            elif(row[0]!="{}/{}".format(header1,header2) and r[0]!="{}/{}".format(header1,header2)):
                try:
                    pass
                    for f in range(1,29):
                        print f,row[f],r[f]
                        corelation(float(row[f])-mean[row[0]],float(r[f])-mean[r[0]])
                    corel[(row[0],r[0])]=ab/math.sqrt(aa*bb)
                    aa=0.0
                    ab=0.0
                    bb=0.0
                except:
                    pass
        j=0
del corel['Dummy']
print corel
c = csv.writer(open("F:\\corelation matrix.csv", "wb"))
x=[]   
y=[]
y.append("Super category/Super Category")

for i,k in y_axis:
    y.append(i)
    
c.writerow(y)
for i,k in y_axis:
    x.append(i.replace(",",""))
    for j,t in y_axis:
        try:
            x.append(corel[(i.replace(",",""),j.replace(",",""))])
        except:
            pass
    c.writerow(x)
    x=[]

                
                
                    
                
                
                
                
       
            
        
           
            
                