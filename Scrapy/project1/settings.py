# Scrapy settings for basics project
#
# For simplicity, this file contains only the most important settings by
# default. All the other settings are documented here:
#
#     http://doc.scrapy.org/topics/settings.html
#

BOT_NAME = 'basics'

SPIDER_MODULES = ['basics.spiders']
NEWSPIDER_MODULE = 'basics.spiders'
#USER_AGENT = "Googlebot/2.1 ( http://www.google.com/bot.html)"
USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64)"

# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'basics (+http://www.yourdomain.com)'
