import time
import schedule
from main import recommend


scheduled_time = "21:27"
schedule.every().monday.at(scheduled_time).do(recommend)
schedule.every().tuesday.at(scheduled_time).do(recommend)
schedule.every().wednesday.at(scheduled_time).do(recommend)
schedule.every().thursday.at(scheduled_time).do(recommend)
schedule.every().friday.at(scheduled_time).do(recommend)
schedule.every().saturday.at(scheduled_time).do(recommend)
schedule.every().sunday.at(scheduled_time).do(recommend)


while True:
    schedule.run_pending()
    time.sleep(1)
