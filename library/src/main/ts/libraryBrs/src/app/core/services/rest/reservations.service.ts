import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RestService } from './rest.service';
const URL = "api/reservations";

@Injectable()
export class ReservationsService {
  public reservations: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }
}
