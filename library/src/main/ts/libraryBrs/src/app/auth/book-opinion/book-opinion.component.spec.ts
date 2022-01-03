import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookOpinionComponent } from './book-opinion.component';

describe('BookOpinionComponent', () => {
  let component: BookOpinionComponent;
  let fixture: ComponentFixture<BookOpinionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookOpinionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookOpinionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
