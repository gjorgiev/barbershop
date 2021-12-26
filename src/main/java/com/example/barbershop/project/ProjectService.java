package com.example.barbershop.project;

import com.example.barbershop.common.CoinGeckoException;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinFullData;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project create(Project project) {
        log.info("Saving new project: " + project.getName());
        return projectRepository.save(project);
    }

    public Collection<Project> list() {
        return projectRepository.findAll();
    }

    @Async
    public void updateAllFromCoingecko() throws CoinGeckoException {
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        Collection<Project> projects = this.list();
        if (client.ping().getGeckoSays().equalsIgnoreCase("(V3) To the Moon!")){
            for (Project project : projects) {
                CoinFullData coinFullData = client.getCoinById(project.getCoingeckoId());
                project.setName(coinFullData.getName());
                project.setDescription(coinFullData.getDescription().get("en"));
                project.setMarketCap(coinFullData.getMarketData().getMarketCap().get("usd"));
                project.setCirculatingSupply(coinFullData.getMarketData().getCirculatingSupply());
                project.setTotalSupply(coinFullData.getMarketData().getTotalSupply());
                project.setSymbol(coinFullData.getSymbol());
                project.setPriceChangePercentage24h(coinFullData.getMarketData().getPriceChangePercentage24h());
                project.setCurrentPrice(coinFullData.getMarketData().getCurrentPrice().get("usd"));
                project.setTotalVolume(coinFullData.getMarketData().getTotalVolume().get("usd"));
                project.setAth(coinFullData.getMarketData().getAth().get("usd"));
                project.setAtl(coinFullData.getMarketData().getAtl().get("usd"));
                project.setAtlChangePercentage(coinFullData.getMarketData().getAtlChangePercentage().get("usd"));
            }
        } else {
            throw new CoinGeckoException("Ping: CoinGecko server is not responding");
        }
        client.shutdown();
        projectRepository.saveAll(projects);
        System.out.println("Database has been updated from CoinGecko");
    }
}
