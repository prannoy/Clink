from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from basics.items import BasicsItem
import csv

class DmozSpider(BaseSpider):
      name = "dmoz"
      allowed_domains = ["dmoz.org"]
      cr = csv.reader(open("C:\\basics\items1.csv","rb"))
      start_urls = []
      for row in cr:
          start_urls.append(row[0])

      def parse(self, response):
         hxs = HtmlXPathSelector(response)
         sites = hxs.select('//li[@class="resZS search-result  status1"]//h3[@class="search-result-title ln24 left"]/a')
         sites1 = hxs.select('//li[@class="resZS search-result  status1"]//div[@class="ln24"]//a')
         sites2 = hxs.select('//li[@class="resZS search-result  status1"]//p[@class="tags ln24"]')
         sites3 = hxs.select('//li[@class="resZS search-result  status1"]//div[@class="small-rating level-6"]/b')
       
         items = []
         for site,site1,site2,site3 in zip(sites,sites1,sites2,sites3):
           item = BasicsItem()
           item['Name'] = site.select('text()').extract()
           item['Location'] = site1.select('text()').extract()
           item['Cuisines'] = site2.select('text()').extract()
           item['Rating'] = site3.select('text()').extract()
           items.append(item)
         return items