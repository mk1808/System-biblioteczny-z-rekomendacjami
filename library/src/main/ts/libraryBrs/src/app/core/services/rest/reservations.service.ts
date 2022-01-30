import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Reservation, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/reservations";

@Injectable()
export class ReservationsService {
  public reservations: BehaviorSubject<Reservation[]> = new BehaviorSubject<Reservation[]>([]);

  constructor(private restService: RestService) { }

  create(reservation: Reservation): Observable<Response<any>> {
    return this.restService.post(`${URL}`, reservation);
  }

  getByUserId(userId: string): any {
    return this.restService.get<Response<Reservation[]>>(`${URL}/user/${userId}`).subscribe((resp: Response<Reservation[]>) => {
      if (resp.content) {
        this.reservations.next(resp.content);
      }
    })
  }

  cancel(id:string): Observable<Response<any>> {
    return this.restService.patch(`${URL}/${id}/cancel`, {});
  }
}
