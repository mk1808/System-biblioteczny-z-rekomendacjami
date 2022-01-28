import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Borrowing, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/borrowings";

@Injectable()
export class BorrowingsService {
  public borrowings: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }

  getByUserId(userId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/user/${userId}`);
  }

  getPastByUserId(userId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/user/${userId}/past`);
  }

  prolong(id:string): Observable<Response<any>> {
    return this.restService.patch(`${URL}/${id}/prolong`, {});
  }

  create(borrowings: Borrowing[]): Observable<Response<any>> {
    return this.restService.post(`${URL}`, borrowings);
  }

  returnBorrowing(bookCopiesIds:string[]): Observable<Response<any>> {
    let stringIds="";
    bookCopiesIds.forEach(id=>stringIds+`&bookCopiesIds=${id}`);
    stringIds=stringIds.substring(1);
    return this.restService.patch(`${URL}/return?${stringIds}`, {});
  }
}
