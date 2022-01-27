import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RestService } from './rest.service';
const URL = "api/borrowings";

@Injectable()
export class BorrowingsService {
  public borrowings: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }
}
