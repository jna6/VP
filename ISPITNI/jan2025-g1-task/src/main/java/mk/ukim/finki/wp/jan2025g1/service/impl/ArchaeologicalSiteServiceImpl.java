package mk.ukim.finki.wp.jan2025g1.service.impl;

import mk.ukim.finki.wp.jan2025g1.model.ArchaeologicalSite;
import mk.ukim.finki.wp.jan2025g1.model.HistoricalPeriod;
import mk.ukim.finki.wp.jan2025g1.model.SiteLocation;
import mk.ukim.finki.wp.jan2025g1.model.exceptions.InvalidArchaeologicalSiteIdException;
import mk.ukim.finki.wp.jan2025g1.repository.ArchaeologicalSiteRepository;
import mk.ukim.finki.wp.jan2025g1.service.ArchaeologicalSiteService;
import mk.ukim.finki.wp.jan2025g1.service.SiteLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.jan2025g1.service.impl.FieldFilterSpecification.*;

@Service
public class ArchaeologicalSiteServiceImpl implements ArchaeologicalSiteService {
    private final ArchaeologicalSiteRepository archaeologicalSiteRepository;
    private final SiteLocationService siteLocationService;

    public ArchaeologicalSiteServiceImpl(ArchaeologicalSiteRepository archaeologicalSiteRepository, SiteLocationService siteLocationService) {
        this.archaeologicalSiteRepository = archaeologicalSiteRepository;
        this.siteLocationService = siteLocationService;
    }

    @Override
    public List<ArchaeologicalSite> listAll() {
        return archaeologicalSiteRepository.findAll();
    }

    @Override
    public ArchaeologicalSite findById(Long id) {
        return archaeologicalSiteRepository.findById(id).orElseThrow(InvalidArchaeologicalSiteIdException::new);
    }

    @Override
    public ArchaeologicalSite create(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        if (name==null || areaSize==null || rating==null || period==null || locationId==null) {
            throw new InvalidArchaeologicalSiteIdException();
        }
        SiteLocation siteLocation = siteLocationService.findById(locationId);
        ArchaeologicalSite archaeologicalSite = new ArchaeologicalSite(name,areaSize,rating,period,siteLocation);
        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public ArchaeologicalSite update(Long id, String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        if (name==null || areaSize==null || rating==null || period==null || locationId==null) {
            throw new InvalidArchaeologicalSiteIdException();
        }
        SiteLocation siteLocation = siteLocationService.findById(locationId);
        ArchaeologicalSite archaeologicalSite = findById(id);
        archaeologicalSite.setName(name);
        archaeologicalSite.setAreaSize(areaSize);
        archaeologicalSite.setRating(rating);
        archaeologicalSite.setPeriod(period);
        archaeologicalSite.setLocation(siteLocation);
        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public ArchaeologicalSite delete(Long id) {
        ArchaeologicalSite archaeologicalSite = findById(id);
        archaeologicalSiteRepository.delete(archaeologicalSite);
        return archaeologicalSite;
    }

    @Override
    public ArchaeologicalSite close(Long id) {
        ArchaeologicalSite archaeologicalSite = findById(id);
        archaeologicalSite.setClosed(!archaeologicalSite.isClosed());
        return archaeologicalSiteRepository.save(archaeologicalSite);
    }
    /**
     * Returns a page of archaeological sites that match the given criteria.
     *
     * @param name     Filters archaeological sites whose names contain the specified text.
     * @param areaSize Filters archaeological sites bigger than the specified area size.
     * @param rating   Filters archaeological sites with a rating greater than the specified value.
     * @param period   Filters archaeological sites based on the historical period.
     * @param locationId Filters archaeological sites by the specified locationId.
     * @param pageNum  The page number.
     * @param pageSize The number of items per page.
     * @return The page of archaeological sites that match the given criteria.
     */
    @Override
    public Page<ArchaeologicalSite> findPage(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId, int pageNum, int pageSize) {
        Specification<ArchaeologicalSite> specification = Specification.allOf(
                filterContainsText(ArchaeologicalSite.class, "name", name),
                greaterThan(ArchaeologicalSite.class, "areaSize", areaSize),
                greaterThan(ArchaeologicalSite.class, "rating", rating),
                filterEquals(ArchaeologicalSite.class, "location.id", locationId),
                filterEqualsV(ArchaeologicalSite.class, "period", period)
        );
        return this.archaeologicalSiteRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize));

    }
}
