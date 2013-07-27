from scrapy.contrib.spiders import CrawlSpider,Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
from scrapy.selector import HtmlXPathSelector

from basics.items import BasicsItem



class DmozSpider(CrawlSpider):
		name = "dmoz"
		allowed_domains = ["dmoz.org"]
		start_urls = ["http://www.prokerala.com/banking/idbi-bank/"]
		rules = [Rule(SgmlLinkExtractor(restrict_xpaths='//div[@class="box box-shadow link-box"]'), 'parse_torrent')]
   
		def parse(self, response):
			hxs = HtmlXPathSelector(response)
			sites = hxs.select('//div[@class="box box-shadow link-box"]/a')
			items = []
			for site in sites:
				item = BasicsItem()
				item['title'] = site.select('text()').extract()
				item['link'] = site.select('@href').extract()
				#item['desc'] = site.select('text()').extract()
				items.append(item)
			return items