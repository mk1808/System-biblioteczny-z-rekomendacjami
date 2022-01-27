import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RestService } from './rest.service';
const URL = "api/users";

@Injectable()
export class UsersService {
  public users: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }
}
