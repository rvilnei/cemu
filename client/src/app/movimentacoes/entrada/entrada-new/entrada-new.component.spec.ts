import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradaNewComponent } from './entrada-new.component';

describe('EntradaNewComponent', () => {
  let component: EntradaNewComponent;
  let fixture: ComponentFixture<EntradaNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EntradaNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntradaNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
