from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from basics.items import BasicsItem
import csv

class DmozSpider(BaseSpider):
      name = "dmoz"
      allowed_domains = ["dmoz.org"]
      cr = csv.reader(open("C:\\basics\item1s.csv","rb"))
      start_urls = []
      a="http://www.prokerala.com/banking/sbi/"
      for row in cr:
          start_urls.append(a+row[0])

      def parse(self, response):
         hxs = HtmlXPathSelector(response)
         sites = hxs.select('//div[@class="thumbnail"]//div[@class="box-shadow pad"]//td[@class="b"]')
         sites1 = hxs.select('//div[@class="box-shadow pad"]//td/strong[1]')
         sites2 = hxs.select('//div[@class="box-shadow pad"]//td/span[1]')
         sites3 = hxs.select('//div[@class="box-shadow pad"]//td/a/strong')
       
         items = []
         for site,site1,site2,site3 in zip(sites,sites1,sites2,sites3):
           item = BasicsItem()
           item['localarea'] = site.select('text()').extract()
           item['address'] = site2.select('text()').extract()
           item['area'] = site1.select('text()').extract()
           item['state'] = site3.select('text()').extract()
           items.append(item)
         return items