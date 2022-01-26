import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibBookStatsComponent } from './lib-book-stats.component';

describe('LibBookStatsComponent', () => {
  let component: LibBookStatsComponent;
  let fixture: ComponentFixture<LibBookStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibBookStatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibBookStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
