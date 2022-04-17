import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Borrowing, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/borrowings";

@Injectable()
export class BorrowingsService {
  public borrowings: BehaviorSubject<Borrowing[]> = new BehaviorSubject<Borrowing[]>([]);
  public pastBorrowings: BehaviorSubject<Borrowing[]> = new BehaviorSubject<Borrowing[]>([]);
  

  constructor(private restService: RestService) { }

  getByUserId(userId:string): any {
    return this.restService.get<Response<Borrowing[]>>(`${URL}/user/${userId}`).subscribe((resp:Response<Borrowing[]>) => {
      if(resp.content){
         this.borrowings.next(resp.content);
      }
     
    })
  }

  getPastByUserId(userId: string): any {
    return this.restService.get<Response<Borrowing[]>>(`${URL}/user/${userId}/past`).subscribe((resp:Response<Borrowing[]>) => {
      if (resp.content) {
        this.pastBorrowings.next(resp.content);
      }
    })
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
