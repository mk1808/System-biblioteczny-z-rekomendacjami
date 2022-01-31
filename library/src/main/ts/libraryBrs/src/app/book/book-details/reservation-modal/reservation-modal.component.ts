import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Book, Reservation } from 'src/app/core/services/rest/api/api';
import { ReservationsService } from 'src/app/core/services/rest/reservations.service';

@Component({
  selector: 'app-reservation-modal',
  templateUrl: './reservation-modal.component.html',
  styleUrls: ['./reservation-modal.component.scss']
})
export class ReservationModalComponent implements OnInit {
  @Input() book: Book={};
  title = "reservation.confirm";
  question = "reservation.confirm.question"
  reservation:Reservation={};

  constructor(private formatterService:FormatterService, public activeModal: NgbActiveModal, private reservationsService:ReservationsService) { }

  ngOnInit(): void {
    console.log(this.book)
  }

  onCancel=()=>{
    debugger;
    this.activeModal.dismiss('Cross click');

  }

  onConfirm=()=>{
    this.reservation.userId="a5edd677-14a9-449b-ab3f-76742762c5f1";
    this.reservation.bookId=this.book.id;
    console.log(this.reservation)
    this.reservationsService.create(this.reservation).subscribe(resp=>{
      console.log(resp);
        this.onCancel();
    })
  
  }

  formatAuthors(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayList(list); }
    return "-"
  }

}
