/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import objects.Adventurer;
import objects.Armor;
import objects.Weapon;

/**
 *
 * @author dszabo1
 */
public class XmlDao {
    
    private static final WeaponDao weaponDao = new WeaponDao();
    
    public void saveAdventurerWitjJAXB(Adventurer adventurer) throws IOException, JAXBException {
        String toWrite;
        
        toWrite = adventurer.getName() + ";"
                + adventurer.getGender() + ";"
                + adventurer.getAge() + ";"
                + adventurer.getRace() + ";"
                + adventurer.getCaste() + ";"
                + adventurer.getAlignment() + ";"
                + adventurer.getStrength() + ";"
                + adventurer.getAgility() + ";"
                + adventurer.getDexterity() + ";"
                + adventurer.getFitness() + ";"
                + adventurer.getHealth() + ";"
                + adventurer.getIntelligence() + ";"
                + adventurer.getBeauty() + ";"
                + adventurer.getWillpower() + ";"
                + adventurer.getAstral() + ";"
                + adventurer.getInitValue() + ";"
                + adventurer.getAttackValue() + ";"
                + adventurer.getDefenseValue() + ";"
                + adventurer.getTargetValue() + ";"
                + adventurer.getFpMax() + ";"
                + adventurer.getÉpMax() + ";"
                + adventurer.getMpMax() + ";"
                + adventurer.getPsiMax() + ";"
                + adventurer.getKp() + ";"
                + adventurer.getXp() + "\n";
        
        File file = new File("./SavedCharacters.xml");
        JAXBContext context = JAXBContext.newInstance(Adventurer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        marshaller.marshal(adventurer, file);
    }
    
    public List<Adventurer> getAllAdventurersWithStax() {
        List<Adventurer> adventurers = new ArrayList<>();
        String file = "/files/savedCharacters.xml";
        
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream stream = new FileInputStream(file);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(stream);
            Adventurer adventurer = new Adventurer();
            
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    
                    if (startElement.getName().getLocalPart().equals("adventurer")) {
                        adventurer = new Adventurer();
                    }
                    
                    switch (startElement.getName().getLocalPart()) {
                        case "name":
                            adventurer.setName(eventReader.nextEvent().toString());
                            continue;
                        case "gender":
                            adventurer.setGender(eventReader.nextEvent().toString());
                            continue;
                        case "age":
                            adventurer.setAge(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "race":
                            adventurer.setRace(eventReader.nextEvent().toString());
                            continue;
                        case "caste":
                            adventurer.setCaste(eventReader.nextEvent().toString());
                            continue;
                        case "alignment":
                            adventurer.setAlignment(eventReader.nextEvent().toString());
                            continue;
                        case "strength":
                            adventurer.setStrength(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "agility":
                            adventurer.setAgility(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "dexterity":
                            adventurer.setDexterity(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "fitness":
                            adventurer.setFitness(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "health":
                            adventurer.setHealth(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "intelligence":
                            adventurer.setIntelligence(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "beauty":
                            adventurer.setBeauty(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "willpower":
                            adventurer.setWillpower(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "astral":
                            adventurer.setAstral(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "ké":
                            adventurer.setInitValue(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "té":
                            adventurer.setAttackValue(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "vé":
                            adventurer.setDefenseValue(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "cé":
                            adventurer.setTargetValue(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "fpMax":
                            adventurer.setFpMax(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "épMax":
                            adventurer.setÉpMax(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "mpMax":
                            adventurer.setMpMax(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "psiMax":
                            adventurer.setPsiMax(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "kp":
                            adventurer.setKp(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        case "tp":
                            adventurer.setXp(Integer.parseInt(eventReader.nextEvent().toString()));
                            continue;
                        
                        case "weaponList":
                            while (true) {
                                if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("weaponList")) {
                                    break;
                                }
                                
                                event = eventReader.nextEvent();
                                if (event.isStartElement()) {
                                    event = eventReader.nextEvent();
                                    Weapon weapon = new Weapon();
                                    weapon = weaponDao.getWeapon(event.toString());
                                    adventurer.addWeapon(weapon);
                                }
                            }
                            continue;
                        case "armor":
                            adventurer.setArmor(new Armor("none", "none", 0, 0, 0));
                            continue;
                    }
                }
                
                if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("adventurer")) {
                    adventurers.add(adventurer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return adventurers;
    }
}
