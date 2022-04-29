class Book(object):
    def __init__(self, obj):  # id, userId, action, bookId, value, date
        self.id = obj["_id"]
        self.get_field(obj, "genres")
        self.get_field(obj, "keyWords")
        self.get_field(obj, "authors")
        self.get_field(obj, "genreIds")
        self.get_field(obj, "keyWordIds")
        self.get_field(obj, "authorIds")
        #self.genres = obj["genres"]
        #self.keyWords = obj["keyWords"]
        #self.authors = obj["authors"]
        #self.genreIds = obj["genreIds"]
        #self.keyWordIds = obj["keyWordIds"]
        #self.authorIds = obj["authorIds"]

    
    def get_field(self, obj, field):
        if field in obj:
            setattr(self, field, obj[field])
        else:
            setattr(self, field, None)
           # self[field] = []
           # self[field] = obj[field];
