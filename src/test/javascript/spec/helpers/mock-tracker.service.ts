import { SpyObject } from './spyobject';
import { AppTrackerService } from '../../../../main/webapp/app/shared/tracker/tracker.service';

export class MockTrackerService extends SpyObject {

    constructor() {
        super(AppTrackerService);
    }

    connect() {}
}
