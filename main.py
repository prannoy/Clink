'''
Created on 12-Jun-2013

@author: PRANNOY.P

'''
import csv
cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv", "rb"))
p = {'Dummy':0}
row_y=0
row_x=0
f=0


for row in cr:
    for i in range(0, 17):
            p[row[i]] = i
    break
del p['Dummy']
print p

rowx = ['Day','Month','Year','Hour']
rowy = ['From Account', 'Card No', 'Merchant Location', 'City', 'Merchant Description', 'Super Category']

for i in rowx:
    row_x=p[i]
    for j in rowy:
        cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))

        row_y=p[j]
        
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
        #print x_axis
        #print y_axis
        
        a=0.0
        table={'Dummy':0}
        for a in y_axis:
            for b in x_axis:
                table[(a,b)]=0
        del table['Dummy']     
        #print table
        
        #for calculating total number of transactions spent in row_x and row_y
        cr = csv.reader(open("F:\\Clink\\Analytics\\Transaction Data Dump1.csv","rb"))
        i=0
        for row in cr:
            if i==0:
                i += 1
            elif (row[14]=="IN" and row[8]=="00"):
                a=float(row[7].replace(",",""))
                if (row[row_y]!="" and row[row_x]!=""):                        
                    y_axis[row[row_y]]+= 1
                    table[(row[row_y],row[row_x])]+=1
                x_axis[row[row_x]] += 1
        
        #Writing into csv   
        c = csv.writer(open("F:\\Clink\\Analytics\\Output\\Heatmaps\\{}_{}.csv".format(header1,header2), "wb"))    
        x=[]   
        y=[]
        y.append("{}/{}".format(header1,header2))
        x_axis=sorted(x_axis.keys())
        y_axis=sorted(y_axis.items(),key=lambda x: x[1],reverse=True)
        
        for i in x_axis:
            y.append(i)
            
        c.writerow(y)
        for i,k in y_axis:
            x.append(i)
            for j in x_axis:
                x.append((table[(i,j)]*100)/k)
            c.writerow(x)
            x=[]
            
