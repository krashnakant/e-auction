/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EauctionTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { BidDetailComponent } from '../../../../../../main/webapp/app/entities/bid/bid-detail.component';
import { BidService } from '../../../../../../main/webapp/app/entities/bid/bid.service';
import { Bid } from '../../../../../../main/webapp/app/entities/bid/bid.model';

describe('Component Tests', () => {

    describe('Bid Management Detail Component', () => {
        let comp: BidDetailComponent;
        let fixture: ComponentFixture<BidDetailComponent>;
        let service: BidService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EauctionTestModule],
                declarations: [BidDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    BidService,
                    JhiEventManager
                ]
            }).overrideTemplate(BidDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BidDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BidService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Bid(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.bid).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
