#!/usr/bin/env python
# coding: utf-8

# In[64]:


with open('sonuc.txt') as f:
    lines = f.readlines()


# In[65]:


totalarr = []
for line in lines:
    totalarr.append(line.split())


# In[80]:


hizArr = []
seferArr = []
bakimArr = []
hiz = 100
for i in range(len(totalarr)):
    seferArr.append(totalarr[i][0])
    bakimArr.append(int(totalarr[i][1]) / 60)
    hizArr.append(hiz)
    hiz += 1


# In[81]:


import matplotlib.pyplot as plt 

#plt.plot(hizArr, seferArr)
plt.plot(hizArr, bakimArr)


# In[ ]:





# In[ ]:





# In[ ]:




