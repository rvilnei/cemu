import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransportadoraNewComponent } from './transportadora-new.component';

describe('TransportadoraNewComponent', () => {
  let component: TransportadoraNewComponent;
  let fixture: ComponentFixture<TransportadoraNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransportadoraNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransportadoraNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
