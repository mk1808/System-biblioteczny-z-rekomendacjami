class Recommendation(object):
    def __init__(self, obj):  # id, userId, action, bookId, value, date
        self._id = obj["_id"]
        self.get_field(obj, "userId")
        self.get_field(obj, "bookId")
        
        self.get_field(obj, "rating")
        self.get_field(obj, "shouldNotRecommend")
        self.get_field(obj, "shouldNotRecommendType")
        self.get_field(obj, "created")
        self.get_field(obj, "deleted")
    
    def get_field(self, obj, field):
        if field in obj:
            setattr(self, field, obj[field])
        else:
            setattr(self, field, None)


 #   def __getitem__(self, item):
  #      return item
    
    def __getitem__(self, key):
        return getattr(self, key)

    def __setitem__(self, key, value):
        setattr(self, key, value)
        
    # def __init__(self,*arg,**kw):
    #     super(Recommendation, self).__init__(*arg, **kw)
        
    # def __init__(self):
    #     pass
    
    # def __getattr__(self, attr):
    #     return self[attr]