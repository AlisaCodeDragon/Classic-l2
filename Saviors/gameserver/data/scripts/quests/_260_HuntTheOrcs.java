package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _260_HuntTheOrcs extends Quest
{

	private static final int ORC_AMULET = 1114;
	private static final int ORC_NECKLACE = 1115;

	public _260_HuntTheOrcs()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(30221);

		addKillId(20468, 20469, 20470, 20471, 20472, 20473);

		addQuestItem(ORC_AMULET, ORC_NECKLACE);

		addLevelCheck(6, 16);
		addRaceCheck(false, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("sentinel_rayjien_q0260_03.htm"))
			st.setCond(1);
		else if(event.equals("sentinel_rayjien_q0260_06.htm"))
			st.finishQuest();
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		switch(npcId)
		{
			case 30221:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "sentinel_rayjien_q0260_02.htm";
					else if(st.getPlayer().getRace() != Race.ELF)
						htmltext = "sentinel_rayjien_q0260_00.htm";
					else
						htmltext = "sentinel_rayjien_q0260_01.htm";
				}
				else if(cond == 1 && st.getQuestItemsCount(ORC_AMULET) == 0 && st.getQuestItemsCount(ORC_NECKLACE) == 0)
					htmltext = "sentinel_rayjien_q0260_04.htm";
				else if(cond == 1 && (st.getQuestItemsCount(ORC_AMULET) > 0 || st.getQuestItemsCount(ORC_NECKLACE) > 0))
				{
					long reward = st.getQuestItemsCount(ORC_AMULET) * 8 + st.getQuestItemsCount(ORC_NECKLACE) * 10;
					htmltext = "sentinel_rayjien_q0260_05.htm";
					st.giveItems(ADENA_ID, reward, 1000);
					st.takeItems(ORC_AMULET, -1);
					st.takeItems(ORC_NECKLACE, -1);
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(st.getCond() > 0)
		{
			if(Rnd.chance(70))
				st.rollAndGive(ORC_AMULET, 1, 14);
			else
				st.rollAndGive(ORC_NECKLACE, 1, 14);
		}
		return null;
	}
}