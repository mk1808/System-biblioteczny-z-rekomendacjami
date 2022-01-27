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
    authors?: Object[];
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

export interface AuthorDto {
    id?: string;
    name?: string;
    surname?: string;
    description?: string;
}

export interface BookAvailabilityDto {
    id?: string;
}

export interface BookCopy {
    id?: string;
    bookId?: string;
    book?: Book;
    status?: string;
}

export interface BookFileColumnDto {
    id?: string;
}

export interface BookFIlterDto {
    id?: string;
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

export interface BookAvailabilityDto {
    id?: string;
}

export interface BookAvailabilityDto {
    id?: string;
}

export interface BookAvailabilityDto {
    id?: string;
}

