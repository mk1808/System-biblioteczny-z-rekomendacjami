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

export interface CreateBookCopiesDto {
    id?: string;
}

export interface FileDto {
    id?: string;
}

export interface GenreDto {
    id?: string;
    name?: string;
}

export interface ImportFileResultDto {
    id?: string;
}

export interface KeyWordDto {
    id?: string;
    name?: string;
    isVerified?: boolean;
}

export interface LibraryDto {
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

export interface MessageDto {
    id?: string;
    user?: AppUser;
    date?: string;
    type?: string;
    ids?: string[];
}

export interface OpinionDto {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    rating?: number;
    comment?: string;
}

export interface RecommendationDto {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    rating?: number;
    shouldNotRecommend?: boolean;
    shouldNotRecommendType?: boolean;
}

export interface RegisterDto {
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

export interface ReservationDto {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;
    eservationDate?: string;
    availabilityStartDate?: string;
    wasBorrowed?: boolean;
}

export interface SeriesDto {
    id?: string;
    name?: string;
}

export interface SurveyDto {
    id?: string;
}

export interface TermsDto {
    id?: string;
    content?: string;
}

export interface UserBooksDto {
    id?: string;
}

export interface UserFilterDto {
    id?: string;
}

export interface UserListElementDto {
    id?: string;
    book?: Book;
    bookId?: string;
    user?: AppUser;
    userId?: string;

    type?: string;
    deleteDate?: string;
    status?: boolean;
}

export interface Response<T> {
    content?: T;
    errors?: any;
    status?:string;
    
}



