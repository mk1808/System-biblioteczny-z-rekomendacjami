import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBookCopyComponent } from './add-book-copy.component';

describe('AddBookCopyComponent', () => {
  let component: AddBookCopyComponent;
  let fixture: ComponentFixture<AddBookCopyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBookCopyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBookCopyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
