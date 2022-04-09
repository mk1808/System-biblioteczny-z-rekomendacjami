import { UserListType } from "src/app/core/enums";

export interface Book {
    id?: string;
    ISBN?: string;
    title?: string;
    publicationYear?: string;
    originalTitle?: string;
    descrpition?: string;
    photo?: string;
    publisherId?: string;
    publisherName?: string;
    authors?: Author[];
    genres?: Object[];
    series?: Object[];
    keyWords?: Object[];

    
}

export interface Address {
    id?: string;
    street?: string;
    houseNo?: string;
    flatNo?: string;
    postcode?: string;
    city?: string;
    district?: string;
    country?: string;
}

export interface AppUser {
    id?: string;
    name?: string;
    surname?: string;
    phoneNo?: string;
    addressId?: number;
    address?: Address;
    mail?: string;
    password?: string;
    roleIds?: string[];
    photo?: string;
    creationDate?: string;
    dezactivationDate?: string;
}

export interface Author {
    id?: string;
    name?: string;
    surname?: string;
    description?: string;
}

export interface Publisher {
    id?: string;
    name?: string;
    description?: string;
}

export interface BookAvailability {
    bookId?: string;
    allBooks?: number;
    available?:number;
    borrowedBooks?: number;
    numberOfReservations?: number;
    keptTooLong?: number;
    status?: string;
}

export interface BookCopy {
    id?: string;
    bookId?: string;
    book?: Book;
    status?: string;
}

export interface BookFileColumn {
    id?: string;
}

export interface BookFIlter {
    ISBN?:string;
    title?:string;
    authorId?:string;
    publisherId?:string;
    genreId?:string;
}

export interface Borrowing {
    id?: string;
    bookCopy?: BookCopy;
    bookCopyId?: string;
    user?: AppUser;
    userId?: string;
    borrowDate?: string;
    returnDate?: string;
    expectedReturnDate?: string;
    numberOfProlongings?: number;
}


export interface CanBorrowBook {
    id?: string;
}

export interface ChangeProposal {
    id?: string;
    value?: string;
    type?: string;
    status?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
}

export interface CreateBookCopies {
    id?: string;
}

export interface File {
    id?: string;
}

export interface Genre {
    id?: string;
    name?: string;
}

export interface ImportFileResult {
    id?: string;
}

export interface KeyWord {
    id?: string;
    name?: string;
    isVerified?: boolean;
}

export interface Library {
    id?: string;
    name?: string;
    phoneNo?: string;
    mail?: string;
    description?: string;
    addressId?: string;
    address?: Address;
    termsId?: string;
    terms?: string;
}

export interface Login {
    mail?: string;
    password?: string;
}

export interface Message {
    id?: string;
    user?: AppUser;
    date?: string;
    type?: string;
    ids?: string[];
}

export interface Opinion {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    rating?: number;
    comment?: string;
}

export interface Recommendation {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    rating?: number;
    shouldNotRecommend?: boolean;
    shouldNotRecommendType?: boolean;
}

export interface Register {
    name?: string;
    surname?: string;
    phoneNo?: string;
    address?: Address;
    mail?: string;
    password?: string;
    passwordRepeat?: string;
    role?: string;
    photo?: string;

}

export interface Reservation {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    reservationDate?: string;
    availabilityStartDate?: string;
    wasBorrowed?: boolean;
}

export interface Series {
    id?: string;
    name?: string;
}

export interface Survey {
    id?: string;
}

export interface Terms {
    id?: string;
    content?: string;
}

export interface UserBooks {
    id?: string;
}

export interface UserFilter {
    id?: string;
}

export interface UserListElement {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;

    type?: UserListType;
    deleteDate?: string;
    status?: boolean;
}

export interface Response<T> {
    content?: T;
    errors?: any;
    status?:string;
    
}



