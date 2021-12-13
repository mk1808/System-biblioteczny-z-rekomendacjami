import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSearchElementComponent } from './book-search-element.component';

describe('BookSearchElementComponent', () => {
  let component: BookSearchElementComponent;
  let fixture: ComponentFixture<BookSearchElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookSearchElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSearchElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
