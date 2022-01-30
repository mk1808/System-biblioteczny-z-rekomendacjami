import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Book, BookCopy, BookFileColumn, BookFIlter, ChangeProposal, Opinion, Response, UserListElement } from './api/api';
import { RestService } from './rest.service';
const URL = "api/books";

@Injectable()
export class BooksService {
  public books: BehaviorSubject<[]> = new BehaviorSubject([]);
  public borrowedBooks: BehaviorSubject<Book[]> = new BehaviorSubject(new Array());

  constructor(private restService: RestService) { }

  getNewest(number:number): Observable<any> {
    return this.restService.get(`${URL}/newest?number=${number}`);
  }

  getFiltered(pageNo:number, size:number, filter:BookFIlter ): Observable<any> {
    return this.restService.get(`${URL}/filtered?pageNo=${pageNo}&size=${size}&bookFilterDto=${encodeURIComponent(JSON.stringify(filter))}`);
  }

  getPopular(number:number): Observable<any> {
    return this.restService.get(`${URL}/popular?number=${number}`);
  }
  
  getById(id:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${id}`);
  }

  getBookCopiesByBookId(id:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${id}/bookCopies`);
  }

  getAvailabilityByBookId(id:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${id}/availability`);
  }

  getOpinionsByBookId(id:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${id}/opinions`);
  }

  getOpinionsByBookIdAndUserId(userId:string, bookId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${bookId}/user/${userId}/opinions`);
  }

  createOpinion(opinion: Opinion): Observable<Response<any>> {
    return this.restService.post(`${URL}/opinions`, opinion);
  }

  updateOpinion(opinion: Opinion): Observable<Response<any>> {
    return this.restService.put(`${URL}/opinions`, opinion);
  }

  createChangeProposal(changeProposals: ChangeProposal[]): Observable<Response<any>> {
    return this.restService.post(`${URL}/changeProposal`, changeProposals);
  }

  createUserListElement(userListElements: UserListElement): Observable<Response<any>> {
    return this.restService.post(`${URL}/userList`, userListElements);
  }

  getUserListElementByUserAndType(userId:string, type:any): Observable<any> {
    return this.restService.get(`${URL}/userList/user/${userId}?type=${type}`);
  }

  getOpinionsByUser(userId:string): Observable<any> {
    return this.restService.get(`${URL}/user/${userId}/opinions`);
  }

  deleteUserListElement(elementId:string): Observable<any> {
    return this.restService.delete(`${URL}/userList/${elementId}`);
  }

  create(book: Book): Observable<Response<any>> {
    return this.restService.post(`${URL}`, book);
  }

  createBookCopies(bookCopies: BookCopy[]): Observable<Response<any>> {
    return this.restService.post(`${URL}/bookCopies`, bookCopies);
  }

  createQRFile(bookCopiesIds:string[]): Observable<any> {
    return this.restService.getWithBody(`${URL}/bookCopies/file`,bookCopiesIds );
  }

  getChangeProposal(bookId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${bookId}/changeProposal`);
  }

  updateChangeProposal(changeProposal:ChangeProposal): Observable<Response<any>> {
    return this.restService.patch(`${URL}/changeProposal`, changeProposal);
  }

  update(book: Book): Observable<Response<any>> {
    return this.restService.put(`${URL}`, book);
  }

  getNewChangeProposals(): Observable<any> {
    return this.restService.get(`${URL}/changeProposal/new`);
  }

  canBorrowBookCopy(bookCopyId: string, userId: string): Observable<Response<any>> {
    return this.restService.post(`${URL}/bookCopies/${bookCopyId}/users/${userId}/canBorrow`, {});
  }

  getBookByBookCopy(bookCopyId:string): Observable<any> {
    return this.restService.get(`${URL}/bookCopies/${bookCopyId}`);
  }

  getColumnsFromImport(file: any): Observable<Response<any>> {
    return this.restService.post(`${URL}/import`, file);
  }

  createColumnsMapping(bookFileColumns: BookFileColumn[]): Observable<Response<any>> {
    return this.restService.post(`${URL}/import/mapping`, bookFileColumns);
  }

  importBooks(bookFileColumns: BookFileColumn[]): Observable<Response<any>> {
    return this.restService.post(`${URL}/import/result`, bookFileColumns);
  }





 /* getFiltered(userFilter:UserFilter): Observable<any> {
    return this.restService.getWithBody(`${URL}/filtered`, userFilter);
  }*/

}

