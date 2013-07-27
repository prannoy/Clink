# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/topics/items.html

from scrapy.item import Item, Field

class BasicsItem(Item):
	Cuisines=Field()
	Name=Field()
	#localarea=Field()
	Location=Field()
	Rating=Field()
	#state=Field()
	
	     #sites = hxs.select('//div[@class="details"]/span[1]')
         #sites1 = hxs.select('//td[@class="details"]/span[2]')
         #sites2 = hxs.select('//td[@class="details"]//div[@class="cuisines"]')
	