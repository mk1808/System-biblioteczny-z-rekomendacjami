import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Reservation, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/reservations";

@Injectable()
export class ReservationsService {
  public reservations: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }

  create(reservation: Reservation): Observable<Response<any>> {
    return this.restService.post(`${URL}`, reservation);
  }

  getByUserId(userId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/user/${userId}`);
  }

  cancel(id:string): Observable<Response<any>> {
    return this.restService.patch(`${URL}/${id}/cancel`, {});
  }
}
