class Book(object):
    def __init__(self, obj):  # id, userId, action, bookId, value, date
        self.id = obj["_id"]
        self.getField(obj, "genres")
        self.getField(obj, "keyWords")
        self.getField(obj, "authors")
        self.getField(obj, "genreIds")
        self.getField(obj, "keyWordIds")
        self.getField(obj, "authorIds")
        #self.genres = obj["genres"]
        #self.keyWords = obj["keyWords"]
        #self.authors = obj["authors"]
        #self.genreIds = obj["genreIds"]
        #self.keyWordIds = obj["keyWordIds"]
        #self.authorIds = obj["authorIds"]

    
    def getField(self, obj, field):
        if field in obj:
            setattr(self, field, obj[field])
           # self[field] = []
           # self[field] = obj[field];
