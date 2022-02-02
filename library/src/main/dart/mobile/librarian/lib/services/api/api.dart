import 'dart:core';

import 'dart:ffi';

class Book {
  final String id;
  final String ISBN;
  final String title;
  final String publicationYear;
  final String originalTitle;
  final String descrpition;
  final String photo;
  final String publisherId;
  final String publisherName;
  final List<Author> authors;
  final List<Genre> genres;
  final List<Series> series;
  final List<String> keyWords;

  Book(
      this.id,
      this.ISBN,
      this.title,
      this.publicationYear,
      this.originalTitle,
      this.descrpition,
      this.photo,
      this.publisherId,
      this.publisherName,
      this.authors,
      this.genres,
      this.series,
      this.keyWords);

  Book.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        ISBN = json['isbn'],
        title = json['title'],
        publicationYear = json['publicationYear'],
        originalTitle = json['originalTitle'],
        descrpition = json['descrpition'],
        photo = json['photo'],
        publisherId = json['publisherId'],
        publisherName = json['publisherName'],
        authors = json['authors'].map<Author>((author)=>Author.fromJson(author)).toList(),
        genres = json['genres'].map<Genre>((genre)=>Genre.fromJson(genre)).toList(),
        series = json['series'].map<Series>((series)=>Series.fromJson(series)).toList(),
        keyWords = json['keyWords'].map<String>((keyWord)=>keyWord.toString()).toList();

  Map<String, dynamic> toJson() => {
        'id': id,
        'ISBN': ISBN,
        'title': title,
        'publicationYear': publicationYear,
        'originalTitle': originalTitle,
        'descrpition': descrpition,
        'photo': photo,
        'publisherId': publisherId,
        'publisherName': publisherName,
        'authors': authors,
        'genres': genres,
        'series': series,
        'keyWords': keyWords
      };
}

class Address {
  final String id;
  final String street;
  final String houseNo;
  final String flatNo;
  final String postcode;
  final String city;
  final String district;
  final String country;
  Address(
    this.id,
    this.street,
    this.houseNo,
    this.flatNo,
    this.postcode,
    this.city,
    this.district,
    this.country,
  );
  Address.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        street = json['street'],
        houseNo = json['houseNo'],
        flatNo = json['flatNo'],
        postcode = json['postcode'],
        city = json['city'],
        district = json['district'],
        country = json['country'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'street': street,
        'houseNo': houseNo,
        'flatNo': flatNo,
        'postcode': postcode,
        'city': city,
        'district': district,
        'country': country
      };
}

class AppUser {
  final String id;
  final String name;
  final String surname;
  final String? phoneNo;
  final int? addressId;
  final Address? address;
  final String mail;
  final String password;
  final List<String> roleIds;
  final String? photo;
  final String? creationDate;
  final String? dezactivationDate;

  AppUser(
    this.id,
    this.name,
    this.surname,
    this.phoneNo,
    this.addressId,
    this.address,
    this.mail,
    this.password,
    this.roleIds,
    this.photo,
    this.creationDate,
    this.dezactivationDate,
  );
  AppUser.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'],
        surname = json['surname'],
        phoneNo = json['phoneNo'],
        addressId = json['addressId'],
        address = json['address'],
        mail = json['mail'],
        password = json['password'],
        roleIds = json['roleIds'].map<String>((roleId)=>roleId.toString()).toList(),
        photo = json['photo'],
        creationDate = json['creationDate'],
        dezactivationDate = json['dezactivationDate'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'surname': surname,
        'phoneNo': phoneNo,
        'addressId': addressId,
        'address': address,
        'mail': mail,
        'password': password,
        'roleIds': roleIds,
        'photo': photo,
        'creationDate': creationDate,
        'dezactivationDate': dezactivationDate
      };
}

class Author {
  final String id;
  final String name;
  final String surname;
  final String description;
  Author(
    this.id,
    this.name,
    this.surname,
    this.description,
  );
  Author.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'],
        surname = json['surname'],
        description = json['description'];
  Map<String, dynamic> toJson() =>
      {'id': id, 'name': name, 'surname': surname, 'description': description};
}

enum BookCopyStatus {
	IDLE, IDLE2, BORROWED, RESERVED, CANBORROW
}

class BookAvailability {
  final String bookId;
	final int allBooks;
	final int available;
	final int borrowedBooks;
	final int numberOfReservations;
	final int keptTooLong;
	final BookCopyStatus status;
  BookAvailability(
    this.bookId,
    this.allBooks,
    this.available,
    this.borrowedBooks,
    this.numberOfReservations,
    this.keptTooLong,
    this.status,
  );
  BookAvailability.fromJson(Map<String, dynamic> json) : 
    bookId = json['bookId'],
    allBooks = json['allBooks'],
    available = json['available'],
    borrowedBooks = json['borrowedBooks'],
    numberOfReservations = json['numberOfReservations'],
    keptTooLong = json['keptTooLong'],
    status = BookCopyStatus.values.firstWhere((e) => e.toString() == json['status'],orElse: () => BookCopyStatus.IDLE);
  Map<String, dynamic> toJson() => {
    'bookId': bookId,
    'allBooks': allBooks,
    'available': available,
    'borrowedBooks': borrowedBooks,
    'numberOfReservations': numberOfReservations,
    'keptTooLong': keptTooLong,
    'status': status
    };
}

class BookCopy {
  final String id;
  final String bookId;
  final Book book;
  final String status;
  BookCopy(
    this.id,
    this.bookId,
    this.book,
    this.status,
  );
  BookCopy.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        bookId = json['bookId'],
        book = Book.fromJson(json['book']),
        status = json['status'];
  Map<String, dynamic> toJson() =>
      {'id': id, 'bookId': bookId, 'book': book, 'status': status};
}

class BookFileColumn {
  final String id;
  BookFileColumn(
    this.id,
  );
  BookFileColumn.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class BookFIlter {
  final String id;
  BookFIlter(
    this.id,
  );
  BookFIlter.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class Borrowing {
  final String? id;
  final BookCopy bookCopy;
  final String bookCopyId;
  final AppUser user;
  final String userId;
  final String? borrowDate;
  final String? returnDate;
  final String? expectedReturnDate;
  final int? numberOfProlongings;
  Borrowing(
    this.id,
    this.bookCopy,
    this.bookCopyId,
    this.user,
    this.userId,
    this.borrowDate,
    this.returnDate,
    this.expectedReturnDate,
    this.numberOfProlongings,
  );
  Borrowing.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        bookCopy = json['bookCopy'],
        bookCopyId = json['bookCopyId'],
        user = json['user'],
        userId = json['userId'],
        borrowDate = json['borrowDate'],
        returnDate = json['returnDate'],
        expectedReturnDate = json['expectedReturnDate'],
        numberOfProlongings = json['numberOfProlongings'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'bookCopy': bookCopy,
        'bookCopyId': bookCopyId,
        'user': user,
        'userId': userId,
        'borrowDate': borrowDate,
        'returnDate': returnDate,
        'expectedReturnDate': expectedReturnDate,
        'numberOfProlongings': numberOfProlongings
      };
}


class UserAvailability {
	final int currentlyBorrowed;
	final int keptTooLong;
  UserAvailability(
    this.currentlyBorrowed,
    this.keptTooLong
  );
  UserAvailability.fromJson(Map<String, dynamic> json) :
    currentlyBorrowed = json["currentlyBorrowed"],
    keptTooLong = json["keptTooLong"];
  Map<String, dynamic> toJson() => {
    'currentlyBorrowed': currentlyBorrowed,
    'keptTooLong': keptTooLong
  };
}

class CanBorrowBook {
  final String? id;
  final Book? book;
	final BookCopy? bookCopy;
	final AppUser? user;
	final BookAvailability? bookAvailabilityDto;
	final UserAvailability? userAvailabilityDto;
	final Bool? isReservedByUser;
	final Bool? canBorrow;
	final String? reservationId;
  CanBorrowBook(
    this.id,
    this.book,
    this.bookCopy,
    this.user,
    this.bookAvailabilityDto,
    this.userAvailabilityDto,
    this.isReservedByUser,
    this.canBorrow,
    this.reservationId
  );
  CanBorrowBook.fromJson(Map<String, dynamic> json)
      : id = json["id"],
        book = json["book"] != null ? Book.fromJson(json["book"]) : null,
        bookCopy = json["bookCopy"] != null ? BookCopy.fromJson(json["bookCopy"]) : null,
        user = json["user"] != null ? AppUser.fromJson(json["user"]) : null,
        bookAvailabilityDto = json["bookAvailabilityDto"] != null ? BookAvailability.fromJson(json["bookAvailabilityDto"]) : null,
        userAvailabilityDto = json["userAvailabilityDto"] != null ? UserAvailability.fromJson(json["userAvailabilityDto"]) : null,
        isReservedByUser = json["isReservedByUser"],
        canBorrow = json["canBorrow"],
        reservationId = json["reservationId"];
  Map<String, dynamic> toJson() => {
    'id': id,
    'book': book,
    'bookCopy': bookCopy,
    'user': user,
    'bookAvailabilityDto': bookAvailabilityDto,
    'userAvailabilityDto': userAvailabilityDto,
    'isReservedByUser': isReservedByUser,
    'canBorrow': canBorrow,
    'reservationId': reservationId
  };
}


class ChangeProposal {
  final String id;
  final String value;
  final String type;
  final String status;
  final Book book;
  final String bookId;
  final AppUser user;
  final String userId;
  ChangeProposal(
    this.id,
    this.value,
    this.type,
    this.status,
    this.book,
    this.bookId,
    this.user,
    this.userId,
  );
  ChangeProposal.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        value = json['value'],
        type = json['type'],
        status = json['status'],
        book = json['book'],
        bookId = json['bookId'],
        user = json['user'],
        userId = json['userId'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'value': value,
        'type': type,
        'status': status,
        'book': book,
        'bookId': bookId,
        'user': user,
        'userId': userId
      };
}

class CreateBookCopies {
  final String id;
  CreateBookCopies(
    this.id,
  );
  CreateBookCopies.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class File {
  final String id;
  File(
    this.id,
  );
  File.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class Genre {
  final String id;
  final String name;
  Genre(this.id, this.name);
  Genre.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'];
  Map<String, dynamic> toJson() => {'id': id, 'name': name};
}

class ImportFileResult {
  final String id;
  ImportFileResult(
    this.id,
  );
  ImportFileResult.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class KeyWord {
  final String id;
  final String name;
  final Bool isVerified;
  KeyWord(
    this.id,
    this.name,
    this.isVerified,
  );
  KeyWord.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'],
        isVerified = json['isVerified'];
  Map<String, dynamic> toJson() =>
      {'id': id, 'name': name, 'isVerified': isVerified};
}

class Library {
  final String id;
  final String name;
  final String phoneNo;
  final String mail;
  final String description;
  final String addressId;
  final Address address;
  final String termsId;
  final String terms;
  Library(
    this.id,
    this.name,
    this.phoneNo,
    this.mail,
    this.description,
    this.addressId,
    this.address,
    this.termsId,
    this.terms,
  );
  Library.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'],
        phoneNo = json['phoneNo'],
        mail = json['mail'],
        description = json['description'],
        addressId = json['addressId'],
        address = json['address'],
        termsId = json['termsId'],
        terms = json['terms'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'phoneNo': phoneNo,
        'mail': mail,
        'description': description,
        'addressId': addressId,
        'address': address,
        'termsId': termsId,
        'terms': terms
      };
}

class Login {
  final String mail;
  final String password;
  Login(
    this.mail,
    this.password,
  );
  Login.fromJson(Map<String, dynamic> json)
      : mail = json['mail'],
        password = json['password'];
  Map<String, dynamic> toJson() => {'mail': mail, 'password': password};
}

class Message {
  final String id;
  final AppUser user;
  final String date;
  final String type;
  final List<String> ids;
  Message(
    this.id,
    this.user,
    this.date,
    this.type,
    this.ids,
  );
  Message.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        user = json['user'],
        date = json['date'],
        type = json['type'],
        ids = json['ids'];
  Map<String, dynamic> toJson() =>
      {'id': id, 'user': user, 'date': date, 'type': type, 'ids': ids};
}

class Opinion {
  final String id;
  final Book book;
  final String bookId;
  final AppUser user;
  final String userId;
  final int rating;
  final String comment;
  Opinion(
    this.id,
    this.book,
    this.bookId,
    this.user,
    this.userId,
    this.rating,
    this.comment,
  );
  Opinion.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        book = json['book'],
        bookId = json['bookId'],
        user = json['user'],
        userId = json['userId'],
        rating = json['rating'],
        comment = json['comment'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'book': book,
        'bookId': bookId,
        'user': user,
        'userId': userId,
        'rating': rating,
        'comment': comment
      };
}

class Recommendation {
  final String id;
  final Book book;
  final String bookId;
  final AppUser user;
  final String userId;
  final int rating;
  final Bool shouldNotRecommend;
  final Bool shouldNotRecommendType;
  Recommendation(
    this.id,
    this.book,
    this.bookId,
    this.user,
    this.userId,
    this.rating,
    this.shouldNotRecommend,
    this.shouldNotRecommendType,
  );
  Recommendation.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        book = json['book'],
        bookId = json['bookId'],
        user = json['user'],
        userId = json['userId'],
        rating = json['rating'],
        shouldNotRecommend = json['shouldNotRecommend'],
        shouldNotRecommendType = json['shouldNotRecommendType'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'book': book,
        'bookId': bookId,
        'user': user,
        'userId': userId,
        'rating': rating,
        'shouldNotRecommend': shouldNotRecommend,
        'shouldNotRecommendType': shouldNotRecommendType
      };
}

class Register {
  final String name;
  final String surname;
  final String phoneNo;
  final Address address;
  final String mail;
  final String password;
  final String passwordRepeat;
  final String role;
  final String photo;
  Register(
    this.name,
    this.surname,
    this.phoneNo,
    this.address,
    this.mail,
    this.password,
    this.passwordRepeat,
    this.role,
    this.photo,
  );
  Register.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        surname = json['surname'],
        phoneNo = json['phoneNo'],
        address = json['address'],
        mail = json['mail'],
        password = json['password'],
        passwordRepeat = json['passwordRepeat'],
        role = json['role'],
        photo = json['photo'];
  Map<String, dynamic> toJson() => {
        'name': name,
        'surname': surname,
        'phoneNo': phoneNo,
        'address': address,
        'mail': mail,
        'password': password,
        'passwordRepeat': passwordRepeat,
        'role': role,
        'photo': photo
      };
}

class Reservation {
  final String id;
  final Book book;
  final String bookId;
  final AppUser user;
  final String userId;
  final String eservationDate;
  final String availabilityStartDate;
  final Bool wasBorrowed;
  Reservation(
    this.id,
    this.book,
    this.bookId,
    this.user,
    this.userId,
    this.eservationDate,
    this.availabilityStartDate,
    this.wasBorrowed,
  );
  Reservation.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        book = json['book'],
        bookId = json['bookId'],
        user = json['user'],
        userId = json['userId'],
        eservationDate = json['eservationDate'],
        availabilityStartDate = json['availabilityStartDate'],
        wasBorrowed = json['wasBorrowed'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'book': book,
        'bookId': bookId,
        'user': user,
        'userId': userId,
        'eservationDate': eservationDate,
        'availabilityStartDate': availabilityStartDate,
        'wasBorrowed': wasBorrowed
      };
}

class Series {
  final String id;
  final String name;
  Series(
    this.id,
    this.name,
  );
  Series.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'];
  Map<String, dynamic> toJson() => {'id': id, 'name': name};
}

class Survey {
  final String id;
  Survey(
    this.id,
  );
  Survey.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class Terms {
  final String id;
  final String content;
  Terms(
    this.id,
    this.content,
  );
  Terms.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        content = json['content'];
  Map<String, dynamic> toJson() => {'id': id, 'content': content};
}

class UserBooks {
  final String id;
  UserBooks(
    this.id,
  );
  UserBooks.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class UserFilter {
  final String id;
  UserFilter(
    this.id,
  );
  UserFilter.fromJson(Map<String, dynamic> json) : id = json['id'];
  Map<String, dynamic> toJson() => {'id': id};
}

class UserListElement {
  final String id;
  final Book book;
  final String bookId;
  final AppUser user;
  final String userId;
  final String type;
  final String deleteDate;
  final Bool status;
  UserListElement(
    this.id,
    this.book,
    this.bookId,
    this.user,
    this.userId,
    this.type,
    this.deleteDate,
    this.status,
  );
  UserListElement.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        book = json['book'],
        bookId = json['bookId'],
        user = json['user'],
        userId = json['userId'],
        type = json['type'],
        deleteDate = json['deleteDate'],
        status = json['status'];
  Map<String, dynamic> toJson() => {
        'id': id,
        'book': book,
        'bookId': bookId,
        'user': user,
        'userId': userId,
        'type': type,
        'deleteDate': deleteDate,
        'status': status
      };
}

class Response {
  final dynamic content;
  final dynamic errors;
  final String status;
  Response(
    this.content,
    this.errors,
    this.status,
  );
  Response.fromJson(Map<String, dynamic> json)
      : content = json['content'],
        errors = json['errors'],
        status = json['status'];
  Map<String, dynamic> toJson() =>
      {'content': content, 'errors': errors, 'status': status};
}
