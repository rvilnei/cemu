import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransportadoraDeleteComponent } from './transportadora-delete.component';

describe('TransportadoraDeleteComponent', () => {
  let component: TransportadoraDeleteComponent;
  let fixture: ComponentFixture<TransportadoraDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransportadoraDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransportadoraDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
